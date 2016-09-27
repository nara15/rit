

from weights import calculate_weight


def process_query(query_words, inverted):

    query_vector = []

    for query, importance in query_words:
       
       n = inverted.get(query)
       ni=0

       if n != None:

           ni = len(n)

           freq = calculate_weight(importance, ni)

           query_vector.append((query, freq))

    return query_vector



def search(dictionary, posting, query, documents):

    sim = 3*[0]
    
    for query, weight in query:

    
        (inicio, long) = dictionary[query]

        lista = posting[inicio: inicio + long]

        for i in range(0,long):

            index = documents.index(lista[i][0])

            sim[index] += lista[i][2] * weight
       
    return sim
        
