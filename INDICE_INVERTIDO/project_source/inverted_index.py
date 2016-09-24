
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

    inicio = 0
    numdocs = 0
    
    for term, docs in inverted.items():

        numdocs = len(docs)

        dictionary[term] = (inicio, numdocs)

        inicio += numdocs

        for doc in docs:

            cant = documents[doc].count(term)

            posting.append([doc, cant, calculate_weight(cant, numdocs)])           


    return dictionary, posting
