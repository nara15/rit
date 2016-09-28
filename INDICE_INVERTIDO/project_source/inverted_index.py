
from weights import calculate_weight

# Creates an preliminary inverted index
# OUTPUT: {term1: [doc1, doc2,..],...}
def term_to_docs(documents, words):

    index = {

	word : set(
				txt for txt,wrds in documents.items() if word in wrds
			)
	for word in words
        }

    invertedIndex = {
	
	k : sorted(v) for k, v in index.items()

    }

    return invertedIndex
    

#   This function creates the full inverted index files
#   INPUT :
#             documents = {doc1: [t1, t2,...],...}
#             inverted = {term1: [doc1, doc2,..],...}
#    OUTPUT:
#            dictionary
#            posting            
def create_inverted_index(documents, inverted):

    dictionary = {}
    posting = []

    norms = dict.fromkeys(documents.keys(),0)

    inicio = 0
    numdocs = 0
    
    for term, docs in inverted.items():

        numdocs = len(docs)

        dictionary[term] = (inicio, numdocs)

        inicio += numdocs

        for doc in docs:

            freq = documents[doc].count(term)

            peso = calculate_weight(freq, numdocs)
            
            posting.append([doc, freq, peso])

            norms[doc] += pow(peso,2)


    return dictionary, posting, norms
