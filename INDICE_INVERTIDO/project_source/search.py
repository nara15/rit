

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




def search(dictionary, posting, query, documents):

    sim = len(documents)*[0]

    resultado = []
    
    for query, weight in query:

    
        (inicio, long) = dictionary[query]

        lista = posting[inicio: inicio + long]

        for i in range(0,long):

            docId = lista[i][0]
            
            index = documents.index(docId)

            sim[index] += lista[i][2] * weight #lista[i][2] peso del término

    sim = sorted(sim,reverse=True)
    
    for s in range(len(sim)):

        if sim[s] != 0.0:

            resultado.append((documents[s],sim[s]))
        else:

            return sim, resultado
        
