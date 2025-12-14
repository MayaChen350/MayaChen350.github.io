import json
from shared import selectedPoemNumbers, poems

romanNumFilePath = "src/lib/data/roman_num_poems.json"


# Source:
# https://labex.io/tutorials/convert-integer-to-roman-numerals-13734

# Idk I didn't feel like coding this today
# Plus this is somehow so much smarter than I had thought
# Well... No actually it uses divisions in a loop which is definitely not efficient
# But since this is only to be run once and it's done I guess it is fine

def to_roman_numeral(num):
    lookup = [
        (1000, 'M'),
        (900, 'CM'),
        (500, 'D'),
        (400, 'CD'),
        (100, 'C'),
        (90, 'XC'),
        (50, 'L'),
        (40, 'XL'),
        (10, 'X'),
        (9, 'IX'),
        (5, 'V'),
        (4, 'IV'),
        (1, 'I'),
    ]
    res = ''
    for (n, roman) in lookup:
        (d, num) = divmod(num, n)
        res += roman * d
    return res


# This is me hating python below (why do I even use it)

romanNumbers = []
if selectedPoemNumbers:
    for poemNum in selectedPoemNumbers:
        romanNumbers.append(to_roman_numeral(poemNum))
else:
    for i in range(len(poems)):
        romanNumbers.append(to_roman_numeral(i + 1))


with open(romanNumFilePath, "x") as f:
    json.dump(romanNumbers, f)
