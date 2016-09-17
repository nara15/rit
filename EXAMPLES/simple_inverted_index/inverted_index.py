
from pprint import pprint as pp
from glob import glob

def parseTexts(fileglob='C:/Users/jonaranjo/OneDrive/TEC/RIT/repo_sample/*.txt'):

	texts, words = {}, set()
	
	for txtFile in glob(fileglob):

		with open(txtFile, 'r') as f :

			txt = f.read().split()

			words |= set(txt)

			texts[txtFile.split('\\')[-1]] = txt

	return texts, words


documents, words = parseTexts()	
"""
print('\nTexts')
pp(texts)
print('\nWords')
print(sorted(words)) """

index = {

	word : set(
				txt for txt,wrds in documents.items() if word in wrds
			)
	for word in words
}

invertedIndex = {
	
	k : sorted(v) for k, v in index.items()

}

pp(invertedIndex)