
import math

from weights import calculate_weight


def process_query(query_words, inverted):

    query_vector = []

    for query, importance in query_words:
       
       docs = inverted.get(query)
       ni = 0

       if docs != None:

           ni = len(docs)

           freq = calculate_weight(importance, ni)

           query_vector.append((query, freq))

    return query_vector

#This functions process a prefix query
def process_prefix_query(prefix, dictionary):


    result = [(key, 1) for key, value in dictionary.items() if key.startswith(prefix)]

    return result


# This functions search a query within the inverted index
def search(dictionary, posting, query, documents):

    sim = len(documents)*[0]

    resultado = []
    
    for query, weight in query:

    
        (inicio, long) = dictionary[query]

        lista = posting[inicio : inicio + long]

        for i in range(0,long):

            docId = lista[i][0]
            
            index = documents.index(docId)

            sim[index] += lista[i][2] * weight #lista[i][2] peso del término

    sim = sorted(sim, reverse = True)
    
    for s in range(len(sim)):

        if sim[s] != 0.0:

            resultado.append((documents[s],sim[s]))
            
        else:

            return sim, resultado


#######################################################################################################3

def norm_query(query):

    res = 0
    
    for q, weight in query:

        res += pow(weight,2)

    return math.sqrt(res)
        

def search_1(dictionary, posting, query, documents, norms):

    sim = len(documents)*[0]

    resultado = []

    norm_q = norm_query(query)
    
    for query, weight in query:

        (inicio, long) = dictionary[query]

        lista = posting[inicio : inicio + long]

        for i in range(0,long):

            docId = lista[i][0]
            
            index = documents.index(docId)
    
            sim[index] += (lista[i][2] * weight) / (norm_q * norms.get(docId)) #lista[i][2] peso del término

    for s in range(len(sim)):

        if sim[s] != 0.0:

            resultado.append((documents[s],sim[s]))
            
    return sim, resultado
        

