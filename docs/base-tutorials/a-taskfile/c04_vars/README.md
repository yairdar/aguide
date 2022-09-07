# Variables

*[Variables](https://taskfile.dev/usage/#variables) 
in official documentation*

## Intro to Variables

[Here is the Taskfile](Taskfile.yaml) to follow along.

> Open the working directory in terminal (I suggest you to use `./working_dir`)

As you may already notice, tasks can make use of variables. For example,  
let's create an initial taskfile in our working directory: `task --init`.

Open the Taskfile.yaml in a text editor, and you will see:

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

## Global Variables

`vars` section is indeed for variables, and one variable is declared there.  
`GREETING` is the name of the variable, `Hello, World!` is its value.

This is a global variable that is declared on the level of a whole taskfile.

Variables are useful in cases when a bunch of tasks use the same value. You can  
assign this value to a variable so when you'll need to edit it you'll have to  
edit it just once.

Let's write a task that uses the same global variable that we already have:

```yaml
  greet-global:
    desc: run this task to print the global GREETING variable declared above
    cmds:
      - echo {{.GREETING}}
```

Call this task: `task greet-global`. Does it print out  `Hello, World!`?

Fine.

## Local Variables

Meanwhile each task can have its own variables. Let's create an example:

```yaml
  greet-local:
    desc: run this task to print the local GREETING variable declared in task
    vars:
      GREETING: Hi! This string is the value of a local variable
    cmds:
      - echo {{.GREETING}}
```

Run this task: `task greet-local`. Notice that the local variable overrides the  
global one with the same name.

By the way:
> When you call a task from another task, the caller's variable overrides the  
> callie's variable with the same name.

Now let's see how taskfiles deal with environmental variables.

[**Next chapter**](../c05_env_vars/README.md)
