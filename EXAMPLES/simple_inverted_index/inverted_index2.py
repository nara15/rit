#consulted from http://aakashjapi.com/fuckin-search-engines-how-do-they-work/

import re
from glob import glob 



# Process each document
# INPUT:   a link to repository
# OUTPUT:
#          texts = {filename: [word1, word2,..], ...}
#          words = {word1, word2,...}
def parseTexts(fileglob='G:/TEC/*txt'):
    
        texts, words = {}, set()
        
        for txtFile in glob(fileglob):
            
                with open(txtFile, 'r') as f:
                    
                        txt = re.findall(r'\S+\b',f.read())
                        
                        words |= set(txt)
                        
                        texts[txtFile.split('\\')[-1]] = txt
                        
        return texts, words

# Creates a dictionary word:{filename1, filename2,...}
# INPUT = TEXTS and WORDS
# OUTPUT = {term1 : {filename1,filename2,...},...}
def terms_to_documents(documents, words):

    index = {
            word : set(
				txt for txt,wrds in documents.items() if word in wrds
			)
            for word in words
        }

    return index
            
# Calculate the frequency of ecah word in a document
# INPUT = [term1, term2,...]
# OUTPUT = {'term1': cant, term2 : cant, ...}
def freq_one_file(termlist):

    freqIndex = {}

    for term in termlist:

        if term in freqIndex.keys():

            currentFreq = freqIndex[term]

            freqIndex[term] = currentFreq + 1

        else:

            freqIndex[term] = 1
            
    return freqIndex

# Calculate the frequency of each character in each document
# INPUT = {filename: [word1, word2, ...], ...}
# OUTPUT = {filename: {word1: cant}, {word2: cant} ...}
def make_frequencies(termlists):

    total = {}

    for filename in termlists.keys():

        total[filename] = freq_one_file(termlists[filename])

    return total



"""
    ################################################
              CREATION OF THE INVERTED INDEX
    ################################################
"""

# Creates the dictionay for the inverted index
# INPUT = {term1 : {filename1,filename2,...},...}
# OUTPUT = {term: (inicio, numdocs),....}
def create_dictionary(terms):

    inicio = 0
    numdocs = 0

    dictionary = {}

    for term, docs in terms.items():

        numdocs = len(docs)

        dictionary[term] = (inicio, numdocs)

        inicio += numdocs

    return dictionary
    


#documents, words = parseTexts()
#td = terms_to_documents(documents, words)
#freq = make_frequencies(documents)
