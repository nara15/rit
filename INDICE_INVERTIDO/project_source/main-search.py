
import re

from save_file.save_file import load_obj

from search import process_prefix_query, search


dictionary = load_obj("dict")
posting = load_obj("posting")
norms = load_obj("norms")
documento_t = load_obj("docs")



def scan_query(input_q):
    
   query_terms = re.findall(r'\-{0,1}\+{0,1}[À-ÿa-zA-Z]+|[À-ÿa-zA-Z]+|\+{0,1}\-{0,1}\"{1}[À-ÿa-zA-Z]+\s[À-ÿA-Za-z]+', input_q.lower())
   result = []

   for q in query_terms:
       if q.startswith('+"'):
           result.append((q[len('+"'):],4))
       elif q.startswith('-"'):
           result.append((q[len('-"'):],1))
       elif q.startswith("+"):
           result.append((q[len('+'):],4))
       elif q.startswith("-"):
           result.append((q[len('-'):],1))
       elif q.startswith('"'):
           result.append((q[len('"'):],2))
       else:
           result.append((q,2))
   return result


query = scan_query('+"persea americana -"sacoglottis holdridgei')

sim,ranking = search(dictionary, posting, query, list(documento_t.keys()),norms)


for i in sorted(ranking, key=lambda x: x[1], reverse=True):

    print(i)
 

