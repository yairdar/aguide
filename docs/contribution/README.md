# Contribution

A guide to Sowftware Gardening

## Get Started

> We consider that you have conda or python installed
> and working environment activated.

```bash
# clone this repo
git clone https://github.com/yairdar/aguide.git  # or via ssh with arg=git@github.com:yairdar/aguide.git

# install mkdocs deps in current envoronment
task docs-builder:install-pip-deps

# try to build site from source
task docs-builder:build-site
```

Now we can see documentation using

> `task docs-builder:serve-source`

!!! tip "Autp Port Forwarding"
    Click on http link in vscode (with ctrl(win), alt(macos))
    and it will create port forward and open link in browser


## Documentation Examples
- [Take a look on documentation trics](./ops-tools/mkdocs-guide.md)

## Nature of Software Architecture
Guide To Comfortable Software Development Process

###  Areas

- Execution, Compute
- Query
- Store Storage/Memory
- Transfer Network

## Publish Docs

- https://towardsdatascience.com/how-to-publish-a-python-package-to-pypi-using-poetry-aa804533fc6f
- https://python-poetry.org/docs/cli/
- https://skerritt.blog/packaging-your-python-project/
- https://gist.github.com/gkhays/a80642ecd8fe476d7220b7940fec5ad0
- https://wiki.dendron.so/notes/c6fd6bc4-7f75-4cbb-8f34-f7b99bfe2d50/#vaults


