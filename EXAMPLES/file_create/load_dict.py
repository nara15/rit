
import pickle

with open("res.txt", "rb") as myFile:
    myNewPulledInDictionary = pickle.load(myFile)

print (myNewPulledInDictionary)
