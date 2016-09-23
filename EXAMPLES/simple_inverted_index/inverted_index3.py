

from inverted_index import parseTexts

from inverted_index2 import make_frequencies




documents, words = parseTexts()	


index = {

	word : set(
				txt for txt,wrds in documents.items() if word in wrds
			)
	for word in words
}




invertedIndex = {
	
	k : sorted(v) for k, v in index.items()

}


freq = make_frequencies(documents)



"""
    This functions create the full inverted index files
    INPUT :
             documents = {doc1: [t1, t2,...],...}
             inverted = {term1: [doc1, doc2,..],...}

    OUTPUT:
             dictionary
             posting
             
"""

def create_inverted_index(documents, inverted):

    dictionary = {}
    posting = {}

    inicio = 0
    numdocs = 0

    key = 0
    
    freq = make_frequencies(documents)

    for term, docs in inverted.items():

        numdocs = len(docs)

        dictionary[term] = (inicio, numdocs)

        inicio += numdocs

        for doc in docs:

            cant = freq[doc].get(term)

            posting[key] = (doc, cant)

            key += 1

    return dictionary, posting
                         

            

    
