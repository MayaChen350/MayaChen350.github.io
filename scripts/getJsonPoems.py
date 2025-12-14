# Source: my gradle build file on my gradle file
# https://github.com/MayaChen350/MayaChen350.github.io/blob/5eefe11b9758d93869e1b652c8a318f7caca5116/site/build.gradle.kts
import requests
import json

poemsFileLink = "https://raw.githubusercontent.com/MayaChen350/Mayascope/refs/heads/master/composeApp/src/commonMain/composeResources/files/poems.txt"
poemsFilePath = "src/lib/data/selected_poems"
# selectedPoemNumbers = [8, 30, 31, 41, 42, 43, 50, 51, 54, 55, 58, 63]
selectedPoemNumbers = None

poems: list[list[str]] = requests.get(poemsFileLink).text.split("///")
poems = list(map(lambda it: it.strip().split("\n\n"), poems))
poems = list(map(lambda poem: list(map(lambda paragraph: paragraph.split("\n"), poem)), poems))

print("Fun fact: There are in total " + str(len(poems)) + " poems! Dang!")

selectedPoems: list[list[str]] = []
if selectedPoemNumbers:
    selectedPoemIndexes = list(map(lambda x: x - 1, selectedPoemNumbers))
    for i, poem in enumerate(poems):
        if i in selectedPoemIndexes:
            selectedPoems.append(poem)
else:
    selectedPoems = poems

with open(poemsFilePath + ".json", "x") as f:
    json.dump(selectedPoems, f)
