import re

from pprint import pprint as pp
from glob import glob
""""
def parseTexts(fileglob='G:/TEC/*.txt'):

	texts, words = {}, set()
	
	for txtFile in glob(fileglob):

		with open(txtFile, 'r') as f :
                        txt = f.read().split()
                        words |= set(txt)
			texts[txtFile.split('\\')[-1]] = txt
	return texts,words
"""

def parseTexts(fileglob='G:/TEC/tablas1/*txt'):
        texts, words = {}, set()
        for txtFile in glob(fileglob):
                with open(txtFile, 'r') as f:
                        txt = re.findall(r'\S+\b',f.read().lower())
                        words |= set(txt)
                        texts[txtFile.split('\\')[-1]] = txt
        return texts, words

""" This function simulates the first creation of an inverted index """
def simulate_index(terms):

        inicio = 0
        numdocs = 0

        for term,docs in terms.items():

                numdocs = len(docs)
                
                print (term, {inicio, numdocs})

                inicio = inicio + numdocs






#ocuments, words = parseTexts()	


"""
index = {

	word : set(
				txt for txt,wrds in documents.items() if word in wrds
			)
	for word in words
}




invertedIndex = {
	
	k : sorted(v) for k, v in index.items()

}
"""

#pp(invertedIndex)



