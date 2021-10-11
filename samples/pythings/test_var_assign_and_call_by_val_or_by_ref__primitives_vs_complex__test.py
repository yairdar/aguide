# This is a sample Python script.

# Press ⌃F5 to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.

def test__sample_variable_label():
    a = 5
    print(id(a))
    a = "sdfs"
    print(id(a))


def aux_method(x: int, s: str, l: list):
    x += 30
    s += "----"
    l += [1, 2, 3]


def test__call_by_value_or_by_reference():
    _x = 11
    _s = "ddf"
    _l = [5, 9]
    aux_method(_x, _s, _l)
    print(f"a={_x}, _s={_s}, _l={_l}")


def test__str_connactation():
    l = ["a", "b", "c", "d", "e"]
    s = ""
    for it in l:
        s += it
    print(s)


def test__str_connactation_join():
    l = ["a", "b", "c", "d", "e"]
    s = "".join(l)
    print(s)


def test__sample_primitive_vs_complex_assignment():
    a = 9
    b = a
    print(f"a={id(a)}, b={id(b)}")

    a += 1
    b -= 1
    print(f"a={a}, b={b}")
    print(f"a={id(a)}, b={id(b)}")

    la = [9]
    lb = la
    la += [1]
    print(f"la={la}, lb={lb}")
    la += [77]



#
#
# def test__sample_primitive_vs_complex_assignment():
#     pass
#     # a = []
    # b = a
    #
    # a += 1
    # b -= 1
    # print(f"a={a}, b={b}")


def main_(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press F9 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
# if __name__ == '__main__':
#     main_('PyCharm')

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
