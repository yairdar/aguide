# Python Compilation / Packing

We apprcieate simple drop-in no-deps executables,
which are genereated by go compiler.

But some of us still don't know go,
and more important there are a lot of software written in python.

So we are looking for ways to pack/compile python program to
single drop-in exe (or at least exe ad resource dir)

## Compilers Overview

- Nuitka
- PyInstaller
- mypyc
- cython

## Nuitka

- [Nuitka Compile to exe on win](https://stackoverflow.com/questions/49831163/compile-python-3-6-script-to-standalone-exe-with-nuitka-on-windows-10)
- https://github.com/Nuitka/NUITKA-Utilities/issues/62
- https://github.com/Nuitka/NUITKA-Utilities/tree/master/hinted-compilation


## Cython

- [4 Attempts at Packaging Python as an Executable](https://tryexceptpass.org/article/package-python-as-executable/)

## PyInstaller

- https://github.com/pyinstaller/pyinstaller/blob/develop/PyInstaller/hooks/hook-pandas.py
