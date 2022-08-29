# Task Directory

*[Task Directory](https://taskfile.dev/usage/#task-directory) in official documentation*

## Task Directory

[Here is the Taskfile](Taskfile.yml) to follow along.

> Open the working directory in terminal (I suggest you to use `./working_dir`)

By default the task runs in the same dircetory where the Taskfile is located,  
but for any task you can specify the dircetory where to run it:  
`dir: path`

Let's create an empty taskfile in our working directory (`task --init`) and  
add a new task to it.

```yaml
  new-text-file:
    desc: make a new text file inside a new directory
    dir: temp 
    cmds:
      - echo "That's how task directories work" > thats_how.txt
```

Try to run the new task: `task new-text-file`.  
Note that the subdirectory gets created if it doesn't yet exist.

The next task will output the content of the text file to the terminal.  
This task should also work in the same subdirectory.

```yaml
  read-text:
    desc: read the text file from a subdirectory
    dir: temp
    cmds:
      - cat thats_how.txt 
``` 

Add it to the Taskfile and try it out:  
`task read-text`

After you have tried tasks with specified working directories it's time  
to clean up our workshop. Let's create a task for this.  
This time will work in the same directory where the Taskfile is, so there's  
no need to specify the directory.

```yaml
  cleanup:
    desc: remove a subdirectory so you can start from scratch
    cmds:
      - rm -r temp
```

Now you can run `task cleanup` and start over any time you want.

## [Next chapter](../c03_including_taskfiles/README.md)
