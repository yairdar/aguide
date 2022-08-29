# yairdar.github.io.publisher
pubish to repo scripts


## Testing bashh applications

```bash
#!/usr/bin/expect -f

set timeout -1
spawn ../4/questions.sh

expect "Your name: "
send -- "expect\n"

expect "Are you human?\r
y/n: "
send -- "n\r"

expect "What is your favorite programming language?\r
Your answer: "
send -- "Java\r"

expect eof
```
