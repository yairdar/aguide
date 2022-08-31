# Getting started

*[Getting Started](https://taskfile.dev/usage/#getting-started) 
and [How to Call One Task from Another](https://taskfile.dev/usage/#calling-another-task) in official documentation*

## Getting started

[Here is the Taskfile](Taskfile.yml) to follow along.

> Open the working directory in terminal (I suggest you to use `./working_dir`)

First install Tasker according to [the instructions](https://taskfile.dev/installation) 
from the official site. I use [the Install Script](https://taskfile.dev/installation/#install-script)  
and it works just fine.

To create a new taskfile in the directory run a command:  
`task –init`  
A new file, Taskfile.yaml, will appear. Open this file in any text editor.  
Its content will look like this:

```yaml
# https://taskfile.dev

version: '3'

vars:
  GREETING: Hello, World!

tasks:
  default:
    cmds:
      - echo "{{.GREETING}}"
    silent: true
```

If you'll run a command `task` with no arguments, Tasker will run the `deafult`  
task, hence it's name.

To make your own task, type it below the exisiting one. Mind indentations,  
so that task name should be indented the same amount as the `default` task  
name and so on. The result should look like this:

```yaml
# https://taskfile.dev

version: '3'

vars:
  GREETING: Hello, World!

tasks:
  default:
    cmds:
      - echo "{{.GREETING}}"
    silent: true

  first-task:
    cmds:
      - echo "My first task runs fine"
```

Now try running `task first-task`. It should execute the command that you have  
written in `cmds` section.

By the way:
> You can print out the list of all tasks with `task -a` command.  
> It's a good practice to add this command to the default task,  
> so you can call it simply typing `task`.

```yaml
tasks:
  default:
    cmds:
      - echo "{{.GREETING}}"
      - task -a
    silent: true
```

So far so good. Now let's add the description to our task. First it's a good  
practice to know what the task's doing. Also, even if the task is self-explanatory,  
a description allows Tasker shell completion to work. For simple tasks like this  
just add a dummy `desc: _` line:

```yaml
  first-task:
    desc: _
    cmds:
      - echo "My first task runs fine"
```

Now let's add more tasks. First of them will be almost exactly the same as before:

```yaml
  get-started:
    desc: _
    cmds:
      - echo "Call any task to get started"
```

The next task is a little bit special: it calls another two tasks.  
Note that calling tasks goes like this: `- task: taskname` in `cmds` section.
  
```yaml
  calling-tasks:
    desc: one task can call another tasks, like this one does
    cmds:
      - task: get-started
      - task: first-task
```

Try running this task:  
`task calling-tasks`  
It should call two other tasks that you have written before.  
Voila! Now we can chain tasks and combine them to fit our needs.

Note:

> You can use shell commands in task to call another task, like this:  
> `- task taskname`  
> but **it's not recommended**. It will work in simple situations when all the  
> tasks are in one Taskfile in your working directory and no environment  
> variables are involved. But in more complex setups it may lead to confusion,  
> so please consider using the native `task: taskname` method.

Even with this basic toolset Tasker can make life much easier.  
But there's much more to it, as you'll see.

[**Next chapter**](../c02_task_directory/README.md)
