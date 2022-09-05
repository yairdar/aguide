// @Library(value="Jenkins_Jenlib@master",changelog=false)

// ===== /SHARED PART ====
// --- globas vars ---
// expected vars
// def jobs_def_dir = env.jobs_def_dir ?: '_cicd'
def jobs_def_dir = '_cicd'

// kw.jnode_label
node() { 

properties([
    parameters([
        string(name: 'seed_job_repo', defaultValue: 'https://github.com/yairdar/aguide.git'),
        string(name: 'seed_job_branch', defaultValue: 'main')
    ])
])

timestamps {

    stage('fetch') {
        deleteDir()
        echo 'Fetch source'
        def scmvars = checkout scm
    }

    stage('create:jobs:local'){
        def jobs_def_pattern = "${jobs_def_dir}/*_jdsl.groovy"
        echo "@info: action.jobdsl=create:jobs:local target.file.pattern='${jobs_def_pattern}'"
        jobDsl targets: [
                jobs_def_pattern
            ].join('\n'),
            lookupStrategy: 'SEED_JOB',
            removedJobAction: 'DELETE',
            removedViewAction: 'DELETE'
    }
    stage('register:arts') {
        sh "find ${jobs_def_dir}"
        archiveArtifacts "${jobs_def_dir}/*"
    }
}}