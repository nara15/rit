
import pickle


def save_to_file(obj, name):

    with open('../files/'+ name + '.pkl', 'wb') as f:

        pickle.dump(obj, f, pickle.HIGHEST_PROTOCOL)


def load_obj(name):
    
    with open('../files/' + name + '.pkl', 'rb') as f:
        
        return pickle.load(f)
