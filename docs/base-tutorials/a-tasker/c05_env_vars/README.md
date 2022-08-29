# Environment Variables

*[Environment Variables](https://taskfile.dev/usage/#environment-variables) 
in official documentation*

## Intro to Environment Variables

[Here is the Taskfile](Taskfile.yml) to follow along.

> Open the working directory in terminal (I suggest you to use `./working_dir`)

Tasks can make use of envronmental variables. Let's explore this feature.

Create a new taskfile (`task --init`), open it in a text editor and add a new  
task to it:

```yaml
greet-env:
    desc: here we declare an enviroment variable right here in task
    cmds:
      - echo ${GREETING}
    env:
      GREETING: Hello, dear user! Here is the environment variable
```

Here we dectale the environment variable in `env` block and call it  
like this: `${GREETING}`

Try to run this task: `task greet-env`

## Global Environment Variables 

This is how we declare environment variables in tasks. We can declare them on  
the taskfile level as well. Let's do it. Add an `env` block after the `vars`  
block in the beginning of the file:

```yaml
version: '3'

vars:
  GREETING: Here we explore using enviroment variables in tasks

env:
  GLOBAL_GREET_ENV: This env var is accessible for all tasks
```

Add a task that will use this variable:

```yaml
  greet-global-env:
    desc: here we use an enviroment variable declared globally in the taskfile
    cmds:
      - echo ${GLOBAL_GREET_ENV}
```

Try it: `task greet-global-env`

## Environment Variables Priority 

Local enviroment variables override the global ones. Add this task to the  
taskfile:

```yaml
  greet-global-env-override:
    desc: here we override an enviroment variable declared globally above
    cmds:
      - echo ${GLOBAL_GREET_ENV}
    env:
     GLOBAL_GREET_ENV: A local env var overrides the global one
```

Run it: `task greet-global-env-override`.

## Passing Environment Variables in Command Line

Now let's try to pass an enviroment variable in the command line while calling  
the task:

`GREETING="Hi from command line" task greet-env`

And with the global variable it will work as well (sure enough):

`GLOBAL_GREET_ENV="And this one is from command line" task greet-global-env`

And even with both of them:

`GLOBAL_GREET_ENV="From command line as well" task greet-global-env-override`

That's expected: the enviroment variable that comes from the *enviroment*  
is more important than the one defined in the taskfile.

## Using Existing Environment Variables in Tasks

Let's use an enviroment variable that we definitely have. Add a task to  
the taskfile:

```yaml
  shell-name:
    desc: In this task we use existing enviroment variable
    cmds:
      - echo ${SHELL}
```

Run it (`task shell-name`), and it will print out the shell path.

## When Variables Get Evaluated to Environment Variables

If task uses a variable with the same name that an enviroment variable has,  
and no variables with this name were declared in taskfile or task itself,  
this variable gets evaluated to the value of the enviroment variable with  
the same name.

For example let's add a task:

```yaml
  shell-name-as-var:
    desc: This task will evaluate a var to the env var from system
    cmds:
      - echo {{.SHELL}}
```

Note that `{{.SHELL}}` is written like an ordinary variable, not like this:  
`${SHELL}`. But the result in this case will be the same!

Anyway to avoid confusion I **recommend** to use `${ENV_VAR}` syntax for  
enviroment variables and `{{.VAR}}` for other variables.

Look what will happen if we'll declare a variable `{{.SHELL}}` in this task:  

```yaml
  shell-var:
    desc: This task won't evaluate a var to the env var from system
    cmds:
      - echo {{.SHELL}}
    vars:
      SHELL: var overrides an env var if called as a var
```

In this case it behaves like an ordinary variable.

## Name Collisions Between Variables and Environment Variables

Let's add a task that uses a local enviroment variable and a global variable  
with the same name (it will work with a local variable as well):

```yaml
  greet-env-and-var:
    desc: global and enviroment variable do not fight, but avoid name collisions
    cmds:
      - echo "{{.GREETING}}"
      - echo ${GREETING}
    env:
      GREETING: The environment variable doesn't override the global variable
```

Run it (`task greet-env-and-var`), and you'll see both values printed out.

They won't override each other, but nontheless it's **not recommended**  
to collide variable names like this. If you can avoid confusion, do.

This was a long chapter with many sections and edge cases, because its subject  
is pretty complicated. The next chapter will be much easier!


## [Next chapter](../c06_deps/README.md)
