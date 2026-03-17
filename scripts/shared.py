import requests

selectedPoemNumbers = [8, 29, 30, 40, 41, 42, 49, 50, 53, 54, 57, 62, 65]

poemsFileLink = "https://raw.githubusercontent.com/MayaChen350/Mayascope/refs/heads/master/composeApp/src/commonMain/composeResources/files/poems.txt"
poemsFilePath = "src/lib/data/poems.json"
selectedPoemsIndexesFilePath = "src/lib/data/selected_poems_indexes.json"

def _getPoemsRaw(filePath="") -> list[str]:
    poemsTxt: str
    if filePath != "":
        with open(filePath) as f:
            poemsTxt = f.read()
    else:
        poemsTxt = requests.get(poemsFileLink).text

    return poemsTxt.split("///")


def getAmountPoems(filePath="") -> int:
   return len(_getPoemsRaw(filePath))


def getPoems(filePath=""):
    poems = _getPoemsRaw(filePath)
    structuredPoems = []

    # fuck this shitt
    a = []
    for poem in poems:
        p = poem.strip()
        if p != "":
            a.append(p.split("\n\n"))
    b = []
    for x in a:
        b2 = []
        for y in x:
            y0 = 
            b2.append()



    # For each poem
    for i, _ in enumerate(poems):
        # Each poem is a list of paragraph of lines

        poems[i] = poems[i].strip()

        # do not keep 
        if not poems[i] != "":
            paragraphs = poems[i].split("\n\n")

            for j, _ in enumerate(paragraphs)
                paragraphs[j] = paragraphs[j].strip()
                if not paragraphs[j] != "" and not paragraphs[j].lower().startswith(("date", "title")):
                    
                    for k, _ in enumerate(paragraphs[j])
                        paragraphs[j][k] = paragraphs




            


    # Split poems into paragraph
    poemsInParagraphs = [p.strip().split("\n\n") for poem in poems if (p := poem.strip()).lower().startswith(("date", "title"))]

    structuredPoems: list[list[list[str]]] = []

    for poemParagraphs in poemsInParagraphs:
        poemParagraphsInLines: list[list[str]] = [paragraph.split("\n") for paragraph in poemParagraphs]
        for index, item in enumerate(my_list):


        structuredPoems.append([for structuredPoem in poemParagraphsInLines if structuredPoem)

    for poem in poems:
        poem = poem.strip().split("\n\n")
        # Split paragraph into lines
        for paragraph in poem:
            paragraph = paragraph.strip()
            # Filter paragraph with poem info
            if :
                poem.remove(paragraph)
            else:
                # Split paragraph into lines
                paragraph = paragraph.split("\n")
                # Clean
                for poemLine in paragraph:
                    poemLine = poemLine.strip()
                    if poemLine == "":
                        paragraph.remove(poemLine)

    print("Fun fact: There are in total " + str(len(poems)) + " poems! Dang!")
    return poems


# poems = list(map(lambda it: it.strip().split("\n\n"), poems))
# poems = list(map(lambda poem: list(map(lambda paragraph: paragraph.split("\n"), poem)), poems))

# Notes for dumb me:
# List comprenhension:
#   new_list = [operation(x) for x in list if cond(x)]
# (This creates a new list WHERE every element is result of operation(x), but cond(x) must be true to be in the final array]
# (I Am The) Walrus operator:
#   if (p := f(x)) == 1 or p == 2
# (This creates a temporary variable in an expression or smth)
