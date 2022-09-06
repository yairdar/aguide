
# Taskfile

## Taskfile for Development Automation

> - What’s the point of Taskfile?
> - Human-friendly automation with a smooth learning curve and serious abilities.

[Offical Site](https://taskfile.dev/)

## Generic Automation Layer

> Taskfile provides unified API for devops needs

### Heterogeneous projects

Heterogeneous projects are projects, 
where part of code written in python / cpp / java /  javascript / typescript 
we have a zoo of “high level tasks definitions”
Maven, conda, pip, pdm, bower, npm, gradle

So high level language that connects them all became a jenkins groovy file, 
or github actions (which is not useful for complex integration tests or MLops needs)

### Project Agnostic Commands

With taskfile based automations it doesn't matter what language or build system is used,
we can always expect the next behavior from project

- `task build`
- `task test`
- `task publish`

And for integration projects

- `task fetch-deps`
- `task run-integration-tests`
- `task deploy`
- `task validate`

It allows building cross-project tooling without relaying or treating 
specific use cases for specific build systems, 
since all the differences are taken care of on the taskfile level. 

## Key advantages of Taskfile

### Human readable, machine executable

- The advantages of YAML for automation
- Like Makefile or bash script, but human readable. Like Markdown docs, but executable. 

### From simplicity to complexity

- Taskfile allows you to make small easy steps first and to achieve more complex behavior by chaining them afterwards.

### Task names that make sense

- Thanks to YAML syntax and description field for each task, it’s easy to track what you’re doing even in complex automations. Just make sure that you give tasks appropriate names and provide explicit descriptions.

### Taskfile works everywhere

- A lightweight utility that helps a lot
- You can use Taskfile on local machines, servers, Docker containers, CI CD systems like Jenkins or Github Actions.
- Most of the time it’s fairly easy to adapt your Taskfiles for a different platform. Usually you can use them as they are without even editing them, or just adding platform-specific tasks to existing ones.

### Easily Parsable by other tools

- Since tasks are written in YAML, they could be parsed in any language, or even in bash console using tools like yq.
- This opens a new horizons to automation tools (like jenlib)
- This could be used for task security validation by enforcing of some rules for different tasks types
- The very thing that a task is either small bash code blocks or composition of other tasks the verification of such block based code is much simpler than analysis of Makefiles, python scripts, bash scripts or groovy scripts.

### IDE support

- [vscode](https://marketplace.visualstudio.com/items?itemName=paulvarache.vscode-taskfile)
- [idea](https://plugins.jetbrains.com/plugin/17058-taskfile)

See more community tools at https://taskfile.dev/community/

### Shell completions

- See [go-task-completions](https://github.com/yairdar/go-task-completions)


## Features

- Separation of build system from automation DAG.
- Efficient and simple parallelism


## Getting started

- [install](https://taskfile.dev/installation/)
- [tutorial](https://yairdar.github.io/base-tutorials/a-tasker/)
- [official docs](https://taskfile.dev/usage/)

