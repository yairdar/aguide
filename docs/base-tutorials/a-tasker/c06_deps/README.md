# Dependencies - Run Tasks in Parallel

*[Dependencies](https://taskfile.dev/usage/#task-dependencies) 
in official documentation*

## Intro to Dependencies - Run Tasks in Parallel

[Here is the Taskfile](Taskfile.yaml) to follow along.

> Open the working directory in terminal (I suggest you to use `./working_dir`)

Here's an important thing:  
**Dependencies in Tasker is the easy way to do tasks in parallel.**

It's convenient in order to speed up tasks. At the same time it adds a limitation:  
dependencies shouldn't depend on each other.

Let's run something in parallel. Create a new taskfile: `task --init`. Open it  
with a text editor and add three new tasks there:

```yaml
download:
    desc: download datasets in parallel
    deps:
      - dataset1
      - dataset2

  dataset1:
    desc: download dataset 1
    cmds:
      - wget https://eforexcel.com/wp/wp-content/uploads/2017/07/5000-Sales-Records.zip 

  dataset2:
    desc: download dataset 2
    cmds:
      - wget https://eforexcel.com/wp/wp-content/uploads/2017/07/10000-Sales-Records.zip
```

The fisrt task calls the second and the third in parallel. Run it and see:  
`task download`.

Okay, now we have our datasets. But they are packed into zip archives.  
Let's unpack them, also in parallel. Meanwhile we'll get familiar with an  
alternative syntax for lists in taskfiles. Add tasks to the taskfile:

```yaml
  extract:
    desc: extract datasets in parallel
    deps: [extract1, extract2]

  extract1:
    desc: extract dataset 1
    cmds:
      - unzip 5000-Sales-Records.zip

  extract2:
    desc: extract dataset 2
    cmds:
      - unzip 10000-Sales-Records.zip
```

Note this line:  
`deps: [extract1, extract2]`  
It is equivalent to the list of lines starting with dashes like this:
```yaml
deps:
  - extract1
  - extract2
```

I don't recommend to write commands in tasks in this form to keep them human  
readable, but for list of dependencies it's fine, as it consists of short words.

There's another important thing about dependencies: they run **before** the  
commands of the task that depend on them.

By the way, we will return to those datasets in further chapters.

[**Next chapter**](../c07_cli_args/README.md)
