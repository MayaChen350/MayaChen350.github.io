import requests

selectedPoemNumbers = [8, 29, 30, 40, 41, 42, 49, 50, 53, 54, 57, 62, 65]

poemsFileLink = "https://raw.githubusercontent.com/MayaChen350/MayaChen350/refs/heads/main/.extras/poems.txt" 
poemsFilePath = "src/lib/data/poems.json"
selectedPoemsIndexesFilePath = "src/lib/data/selected_poems_indexes.json"


def NotEmpty(string: str):
    return string != ""


def _getPoemsRaw(filePath="") -> list[str]:
    poemsTxt: str
    if filePath != "":
        with open(filePath) as f:
            poemsTxt = f.read()
    else:
        poemsTxt = requests.get(poemsFileLink).text

    poemsTexts = poemsTxt.split("///")
    poemsTextsStripped = [p.strip() for p in poemsTexts]
    return [p for p in poemsTextsStripped if NotEmpty(p)]


def getAmountPoems(filePath="") -> int:
   return len(_getPoemsRaw(filePath))

def getPoems(filePath=""):
    poems: list[str] = _getPoemsRaw(filePath)

    structuredPoems: list[ list[ list[str] ] ]  = []

    # Each poem has:
    lines: list[str] # or a paragraph 
    # Contained in:
    paragraphs: list[ list[str] ] # or a poem

    for poemText in poems:
       poemInParagraphs = poemText.split("\n\n")

       paragraphs = []
       for poemPara in poemInParagraphs:
           poemParaCleaned = poemPara.strip()
           if NotEmpty(poemParaCleaned) and not poemParaCleaned.startswith(("date", "title")):

               lines = []
               poemParaCleanedLines = poemParaCleaned.split("\n")
               for li in poemParaCleanedLines:
                   lineCleaned = li.strip()
                   if NotEmpty(lineCleaned):
                       lines.append(lineCleaned)

               if len(lines) > 0:
                 paragraphs.append(lines)

       if len(paragraphs) > 0:
            structuredPoems.append(paragraphs)

    print("Fun fact: There are in total " + str(len(poems)) + " poems! Dang!")
    return structuredPoems


# poems = list(map(lambda it: it.strip().split("\n\n"), poems))
# poems = list(map(lambda poem: list(map(lambda paragraph: paragraph.split("\n"), poem)), poems))

# Notes for dumb me:
# List comprenhension:
#   new_list = [operation(x) for x in list if cond(x)]
# (This creates a new list WHERE every element is result of operation(x), but cond(x) must be true to be in the final array]
# (I Am The) Walrus operator:
#   if (p := f(x)) == 1 or p == 2
# (This creates a temporary variable in an expression or smth)
