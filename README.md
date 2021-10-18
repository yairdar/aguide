# Aguide

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

## The Full picture

???+ note "FULL Picture"
     ```mermaid
     graph LR
         A --> B
     ```

## Documentation Examples

- [Take a look on documentation trics](./docs/guides/mkdocs-guide.md)

