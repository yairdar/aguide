# Including Taskfiles

*[Including other Taskfiles](https://taskfile.dev/usage/#including-other-taskfiles) 
in official documentation*

## Intro to Including Taskfiles

[Here is the Taskfile](Taskfile.yml) to follow along.

> Open the working directory in terminal (I suggest you to use `./working_dir`)

Until now we only tried to run the tasks that are in the Taskfile.yaml in our  
working directory. In many cases it's enough, but there is an option  
to include external taskfiles to the main one.

Let do it!

Create a new taskfile in the working directory: `task --init`.

Don't add anything to this file yet. Make a new file:

`touch greet.tasks.yaml`

Now let's make a task in our external file. Open `greet.tasks.yaml` in a text  
editor that you prefer and add the content:

```yaml
# https://taskfile.dev

version: '3'

tasks:
  simple_greet:
    desc: 'Call this task like this: task from-another-file:simple_greet'
    cmds:
      - echo "Hi from included file"
```

This is a whole valid taskfile that we will include to our main Taskfile.yaml.  
To do it, let's add to our taskfile (after `version: '3'` line):

```yaml
includes:
  from-another-file:
    taskfile: greet.tasks.yaml
```

Try to call a task from the included file:
`task from-another-file:simple-greet`

If it writes out `Hi from included file`, then we're good to go.

Note:
> It's possible to run tasks from taskfiles that aren't named Taskfile.yaml.  
> To do this, use `task -t taskfile-name.yaml taskname` command.  
> For instance, try `task -t greet.tasks.yaml simple-greet`

## Including directories

We can include not only files, but directories. In this case the Taskfile.yaml  
from this directory gets included so we access its tasks from our main taskfile.

Let's include the directory. When To do this, create a new directory:

`mkdir greet-dir`

Go to this directroy (`cd greet-dir`) and create a new taskfile (`task --init`).

Open this new Taskfile.yaml with a text editor and overwrite its content with  
following block:

```yaml
# https://taskfile.dev

version: '3'

tasks:
  simple-greet:
    desc: 'Call this task like this: task from-subdir:simple_greet'
    cmds:
      - echo "Hello from a subdirectory!"

```

Now go back to the worknig directory (`cd ..`) and open the main Taskfile.yaml  
in the text editor. Add the following line to the `includes:` block:

`from-subdir: ./greet_dir`

The result should look like this:

```yaml
includes:
  from-another-file:
    taskfile: greet.tasks.yaml
  from-subdir: ./greet_dir
```

Save the file and try to run:  
`task from-subdir:simple-greet`

If it writes out `Hello from a subdirectory!`, then it works as expected.

And that's basically it, except for...

## Optional includes.

By default it the included taskfile is not found the main taskfile will  
not work. If you add `optional: true` line to the include, it's not the  
case any more. The main taskfile will work just fine even if this particular  
include is not found. Here's the example:

```yaml
includes:
  from-another-file:
    taskfile: greet.tasks.yaml
  from-subdir: ./greet_dir
  from-nonexisting-file:
    taskfile: taskfile.thats.not.there.yaml
    optional: true
```

Note that `from-nonexisting-file` include is optional, so the taskfile works  
fine even if the file's not there.

Now you know how to include external Taskfiles.

Note:
> You **can't chain includes**, like including the taskfile that already  
> includes other taskfiles.  
> You can walkaround this limitation calling tasks from another taskfiles with  
> commands, like `task -t taskfile-name.yaml taskname`, that's where this  
> syntax comes in handy. But whenever you can avoid complexity, please do.

[**Next chapter**](../c04_vars/Taskfile.yaml)
