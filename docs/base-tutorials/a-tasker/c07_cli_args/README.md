# Passing CLI Arguments to the Task

*[CLI Arguments](https://taskfile.dev/usage/#forwarding-cli-arguments-to-commands) 
in official documentation*

## Intro to Passing CLI Arguments to the Task 

[Here is the Taskfile](Taskfile.yml) to follow along.

> Open the working directory in terminal (I suggest you to use `./working_dir`)

Remember that you can pass an [environment variable](../c05_env_vars/README.md)
to the task in command line?  
You might as well use CLI arguments as a special variable inside your tasks.  
Let's learn how.

Inside the task this varibale will look like this: {{.CLI_ARGS}}

Create a new taskfile (`task --init`) and add a task to it:

```yaml
  greet-cli:
    desc: 'usage: task greet-cli -- username'
    cmds:
      - echo "Hello dear {{.CLI_ARGS}}"
```

Call that task with your name as an argument like this:  
`task greet-cli -- username`

It should greet you by username.  
Obviously this feature is useful in more realistic applications as well.  
Like one in the [next chapter](../c08_requests/README.md).


[**Next chapter**](../c08_requests/README.md)
