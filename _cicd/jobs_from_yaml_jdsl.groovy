@Grab('org.yaml:snakeyaml:1.17')
import org.yaml.snakeyaml.Yaml

// see https://job-dsl.herokuapp.com/
def gitext = readFileFromWorkspace('_cicd/_jobs_from_yaml_jobs.yml')
out.println(gitext)
def jobs_cfg = new Yaml().load(gitext)


def create_multibranchPipelineJobGitHub(job_ctx, job_props){    
    def github_api_uri = job_props.github_api_uri ?: 'https://api.github.com' 
    
    job_ctx.with {
        factory {
            workflowBranchProjectFactory {
                scriptPath(job_props.jenkins_pipeline_path)
            }
        }

        branchSources {
            github {
                id("2022-06-13--193722--fena--${job_props.full_job_path}")
                apiUri(github_api_uri)

                scanCredentialsId(job_props.cred_id)
                checkoutCredentialsId(job_props.cred_id)
                repoOwner(job_props.repo_org)
                repository(job_props.repo_name)
                buildForkPRHead(false)
                buildForkPRMerge(false)
                buildOriginBranch(true)
                buildOriginBranchWithPR(false)
                buildOriginPRHead(false)
                buildOriginPRMerge(true)

            }
        }
        orphanedItemStrategy {
            discardOldItems {
                numToKeep(job_props.num_to_keep)
            }
        }
    }
}


def create_multibranchPipelineJob(job_ctx, job_props){
    job_ctx.with {
        factory {
            workflowBranchProjectFactory {
                scriptPath(job_props.jenkins_pipeline_path)
            }
        }
        branchSources {
            configure { node ->
                node / sources(class: 'jenkins.branch.MultiBranchProject$BranchSourceList') / data / 'jenkins.branch.BranchSource' / source(class: 'jenkins.plugins.git.GitSCMSource') {
                    remote job_props.git_url
                    credentialsId job_props.cred_id
                    includes job_props.branches__includes
                    excludes job_props.branches__excludes
                    ignoreOnPushNotifications job_props.ignoreOnPushNotifications
                    id "2020-10-13--050222--cmPJ---${job_props.full_job_path}"
                    traits {
                        'jenkins.plugins.git.traits.BranchDiscoveryTrait'()
                        'jenkins.plugins.git.traits.CloneOptionTrait' () {
                            extension(class: 'hudson.plugins.git.extensions.impl.CloneOption') {
                            shallow 'false'
                            noTags 'false'
                            reference ''
                            depth '0'
                            honorRefspec 'false'
                            }
                        }
                        'jenkins.plugins.git.traits.LocalBranchTrait'(){
                            extension(class: 'hudson.plugins.git.extensions.impl.LocalBranch'){
                                localBranch('**')
                            }
                        }
                    }
                }
            }
        }
        orphanedItemStrategy {
            discardOldItems {
                numToKeep(job_props.num_to_keep)
            }
        }
    }
}


def create_pipelineJob(job_ctx, job_props){
    // ---- job_props structure ----
    def fprops = [:]
    fprops.description = job_props.description
    fprops.git_url = job_props.git_url
    fprops.branch = job_props.branch
    fprops.jenkins_pipeline_path = job_props.jenkins_pipeline_path

    fprops.num_to_keep = job_props.num_to_keep ?: 28
    fprops.cred_id = job_props.cred_id ?:  "sys_ydzvulon_ssh"
    fprops.quietPeriod = job_props.quietPeriod ?:  3
    fprops.params = job_props.params
    fprops.cron = job_props.cron ?: ""


    // ---- job structure ----
    job_ctx.with {
        description(fprops.description)
        keepDependencies(false)

        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(fprops.git_url)
                            credentials(fprops.cred_id)
                        }
                        branch(fprops.branch)
                    }
                }
                scriptPath(fprops.jenkins_pipeline_path)
            }
        
        }
        // TODO: use active flag
        disabled(false)
        quietPeriod(fprops.quietPeriod)
            //// #### !!!! JOBDSL 1.77
    //         triggers {
    //     cron('@daily')
    // }
        if (fprops.cron){
            triggers {
                cron("${fprops.cron}")
                // cron('''5 * * * *''')
            }
        }   
        properties {
            disableConcurrentBuilds()
            // gitLabConnection {
            //     gitLabConnection('conn-gitlab')
            // }  properties {
            //// #### !!!! JOBDSL 1.77
            // pipelineTriggers {
            // //     if (fprops.cron){
            //         triggers {
            // //             // cron("${fprops.cron}")
            //             cron('''5 * * * *''')

            //         }   
            // //     }
            // }

        }

        if (fprops.params){
            parameters {
                if (fprops.envvars){
                    for (pr in fprops.envvars){
                        if (pr.kind && pr.kind == 'textParam'){
                            textParam(pr.name, pr.value, pr.desc)
                        }else{
                            stringParam(pr.name, pr.value, pr.desc)
                        }
                    }
                }                
                for (pr in fprops.params){
                    if (pr.kind && pr.kind == 'textParam'){
                        textParam(pr.name, pr.value, pr.desc)
                    }else{
                        stringParam(pr.name, pr.value, pr.desc)
                    }
                }
                if(fprops.params_mul){
                    for (pr in fprops.params_mul){
                        textParam(pr.name, pr.value, pr.desc)
                    }                    
                }
            }
        }
        configure {
            it / 'properties' / 'jenkins.model.BuildDiscarderProperty' {
                strategy {
                    'daysToKeep'('-1')
                    'numToKeep'('28')
                    'artifactDaysToKeep'('-1')
                    'artifactNumToKeep'('-1')
                }
            }
            it / 'properties' / 'com.sonyericsson.rebuild.RebuildSettings' {
                'autoRebuild'('false')
                'rebuildDisabled'('false')
            }
        }
    }
}


for (task in jobs_cfg.jobs) {
    def full_job_path = "${task.key}"
    def job_def_val = task.value
    def props = job_def_val.props

	if ( job_def_val.active != null && job_def_val.active == false ){
		out.println("@log.warn=target.skip target.name=${full_job_path} action.name=skip action.reason='not active'")
		continue
	}

    props['full_job_path'] = full_job_path

    def arr = full_job_path.split('/')
    if (arr.size() > 1){
        // TODO: check if it works for subfolders
        def job_rel_dir = arr[0..-2].join('/')
        folder(job_rel_dir) {
            description('Autogenerated')
        }
    }

    if (props.job_type == 'multibranchPipelineJob'){
        job_ctx = multibranchPipelineJob("${full_job_path}")
        create_multibranchPipelineJob(job_ctx, props)
    }

    if (props.job_type == 'multibranchPipelineJobGitHub'){
        job_ctx = multibranchPipelineJob("${full_job_path}")
        create_multibranchPipelineJobGitHub(job_ctx, props)
    }    

    if (props.job_type == 'pipelineJob'){
        job_ctx = pipelineJob("${full_job_path}")
        create_pipelineJob(job_ctx, props)
    }
}
