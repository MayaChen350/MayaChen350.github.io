import requests

# selectedPoemNumbers = [8, 29, 30, 40, 41, 42, 49, 50, 53, 54, 57, 62]
selectedPoemNumbers = None

poemsFileLink = "https://raw.githubusercontent.com/MayaChen350/Mayascope/refs/heads/master/composeApp/src/commonMain/composeResources/files/poems.txt"
poemsFilePath = "src/lib/data/selected_poems.json"

poems: list[list[str]] = requests.get(poemsFileLink).text.split("///")
poems = list(map(lambda it: it.strip().split("\n\n"), poems))
poems = list(map(lambda poem: list(map(lambda paragraph: paragraph.split("\n"), poem)), poems))

print("Fun fact: There are in total " + str(len(poems)) + " poems! Dang!")
