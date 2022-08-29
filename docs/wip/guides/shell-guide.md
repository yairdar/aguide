# Shell Guide

## Amazing Shell Tips

- https://www.shell-tips.com/bash/loops/

## Interactive Shell

- https://www.baeldung.com/linux/bash-interactive-prompts

## oh my zsh

- [zsh:oh-my-zsh:plugins](https://github.com/ohmyzsh/ohmyzsh/wiki/Plugins)
- docker-compose

### Zsh Completions
 
- https://scriptingosx.com/2019/07/moving-to-zsh-part-5-completions/
- https://zsh.sourceforge.io/Doc/Release/Completion-System.html


## Bash Script Parts

Following next naming convention

!!! info Schema

    ```yaml
    layout: |-
      ### TOPIC. CHAPTER_NUM__NO_ZEROS. CHAPTER_NAME
    terms:
      TOPIC: name id of series. here='Bash Script Parts'
      CHAPTER_NUM__NO_ZEROS: |-
        desc: chapter number in order of entry in script or complexity
        no zeros: to elimitate zero interpretation issues no zeros allowed
      CHAPTER_NAME: name of the chapter sample='Shebang Line'
    ```

### Bash Script. P11. Shebang Line

**How does /usr/bin/env work in a Linux shebang line?**

!!! question What is `shebang`

    > First line in bash / sh scripts usually looks like

    ```bash
    #!/usr/bin/env python3
    ```

!!! question **How does /usr/bin/env work in a Linux shebang line?** 

    > `_origin_link_` [stack-overflow-question](https://stackoverflow.com/questions/43793040/how-does-usr-bin-env-work-in-a-linux-shebang-line)

    `env` is the name of a Unix program. If you read the manual (`man env`) you can see that one way to use it is `env COMMAND`, where in your case, `COMMAND` is `python3`.

    According to the manual, this will

    > Set each `NAME` to `VALUE` in the environment and run `COMMAND`.

    Running env alone will show you what NAMEs and VALUEs are set:

    ```sh
    $ env
    TERM=xterm-256color
    SHELL=/bin/bash
    PATH=/usr/local/bin:/usr/bin:/bin:/usr/local/sbin:/usr/sbin:/sbin
    ```

    Therefore, `/usr/bin/env python3` is an instruction to set the `PATH` (as well as all the other `NAME+VALUE` pairs), and then run `python3`, using the first directory in the `PATH` that contains the `python3` executable.

!!! tip **Reference**
    - [How to use the /usr/bin/env command in a shell script](https://www.diskinternals.com/linux-reader/usr-bin-bash/)
    - [Why is it better to use "#!/usr/bin/env NAME" instead of "#!/path/to/NAME" as my shebang?](https://unix.stackexchange.com/questions/29608/why-is-it-better-to-use-usr-bin-env-name-instead-of-path-to-name-as-my)


=== "Sdf"

    sdf
    sdf

=== "sdf"
