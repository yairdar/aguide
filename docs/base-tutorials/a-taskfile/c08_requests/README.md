# Requests - an Applied Taskfile Examples

[Here is the Taskfile](Taskfile.yaml) to follow along.

> Open the working directory in terminal (I suggest you to use `./working_dir`)

Remember we've fetched some example datasets from the internet in
[chapter 06](../c06_deps/README.md)?  
Let's do it once again. Create a taskfile: `task --init`. Add tasks:

```yaml
  download-dataset:
    desc: download a dataset
    cmds:
      - wget https://eforexcel.com/wp/wp-content/uploads/2017/07/10000-Sales-Records.zip

  extract-dataset:
    desc: extract dataset
    deps: download-dataset
    cmds:
      - unzip 10000-Sales-Records.zip
```

Note that we add a dependency to `extract-dataset` so when we call this task  
it first executes the `download-dataset` task. Call the extract task:  
`task extract-dataset`

See what happened: first the `download-dataset` task is executed and only then  
the `extract-dataset` runs. Nice! Now we have an example dataset to query.

This randomly-generated dataset mimics global sales data. Open  
[the .csv file](10000%20Sales%20Records.csv) to see its content format.

Say we need a task that will print out sales of particular product in particular  
region of the world. Let's use a taskfile variables: one to set the region,  
one to set the product. Add to the `vars` block of the taskfile:

```yaml
vars:
  GREETING: Hello, World!
  REGION: Asia
  PRODUCT: Cereal
```

Add a task that will use these variables to query our dataset using `grep`:

```yaml
  query-with-global-vars:
      desc: query a dataset with the variables set globally above
      cmds:
        - cat "10000 Sales Records.csv" | grep "{{.REGION}}" | grep {{.PRODUCT}}
```

Fine. Now we can make a task that will accept an additional
[CLI argument](../c07_cli_args/README.md)  
to focus our request. It will use the same variables for region and product,  
but it will take an additional argument as well. Note the multi-line syntax:


```yaml
  query-with-cli-args:
      desc: query a dataset with the variables set globally above
      cmds:
      - |-
        cat "10000 Sales Records.csv" \
        | grep "{{.REGION}}" | grep {{.PRODUCT}} \
        | grep {{.CLI_ARGS}}
```

Try to call it with a country name as an additional argument, for instance:  
`task query-with-cli-args -- Kazakhstan`

Without a CLI argument it will throw an error, because `grep` needs one argument.

Now let's make another task with local variables overriding global ones:

```yaml
  query-with-local-vars:
      desc: query a dataset with the variables set globally above
      cmds:
        - cat "10000 Sales Records.csv" | grep "{{.REGION}}" | grep {{.PRODUCT}}
      vars:
        REGION: Europe
        PRODUCT: Meat
```

Try this task `task query-with-local-vars` and see data on meat sales in Europe.

Let's make a task that will save our query results into a file, and the name  
of this file will be passed as a CLI argumet:


```yaml
  query-save-to-file:
      desc: query a dataset with the variables set globally above
      cmds:
      - |-
        cat "10000 Sales Records.csv" \
        | grep "{{.REGION}}" | grep {{.PRODUCT}} \
        > {{.CLI_ARGS}}
      vars:
        REGION: Europe
        PRODUCT: Meat
```

Call this task with an output file name as a CLI argument, for instance:  
`task query-save-to-file -- euro-meat.csv`

Now you can open the output file and make sure that it contains the result  
of our query.

## That's it!

Thank you for attending this tutorial.  
Now you know how to use Taskfile. Feel free to apply this knowledge.  
Hope Taskfile will appear a useful tool for you and this tutorial helped you  
to learn its features and feel its human-friendly approach to task automation.
