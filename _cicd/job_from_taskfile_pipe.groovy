@Library('JenkinsLib_Jenlib') _

def kwj = [
    'scmvars': null,
    'task_parse_result': [:],
    'params':[
        'taskwork_dir': env.taskwork_dir ?: '.',
        'taskfile_path': env.taskfile_path ?: 'Taskfile.yml',
        'entrypoint_task': env.entrypoint_task ?: 'ci-flow',
        'params_slug': env.params_slug ?: '',
        'node_selector:': env.node_selector ?: ''
    ]
]

println "kwj.params.node_selector=${kwj.params.node_selector}"
println "env.node_selector=${env.node_selector}"

node ("${env.node_selector ?: ''}") {
    
    stage('start'){
        sh 'echo Init Flow'
        def scmvars = checkout scm

        if (kwj.params.params_slug) {
            def loaded_yaml = readYaml file: kwj.params.params_slug
            kwj.params << loaded_yaml
        }        
    }
    // , gitToolName: 'git')
    stage('resolve-deps'){
        // withCredentials([gitUsernamePassword(credentialsId: env.cred_id]) {
        //     // sh "task -d ${kwj.params.taskfile_path} resolve-deps"
        //     sh """git clone https://github.com/nostro-im/nostro-datalake-config.git"""
        //     sh """git clone https://github.com/nostro-im/nostro-analytic-tools.git"""
        // }
    }

    dir(kwj.params.taskwork_dir){
        echo "would unflod ${kwj.params.taskcmd}"
        jen.step_stages_from_tasks(
            kwj, 
            '.' ,
            kwj.params.taskfile_path,
            kwj.params.entrypoint_task
        )
    }

    stage('finis'){
        sh 'echo Over Flow'
    }
}