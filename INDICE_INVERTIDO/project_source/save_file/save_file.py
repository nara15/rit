
import pickle


def save_to_file(obj, name):

    with open(name + '.pkl', 'wb') as f:

        pickle.dump(obj, f)


def load_obj(name):
    
    with open(name + '.pkl', 'rb') as f:
        
        return pickle.load(f)
