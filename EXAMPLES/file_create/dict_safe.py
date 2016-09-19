import pickle

## I am making up a dictionary here to show you how this works...
## Because I want to store this outside of this single run, it could be that this
## dictionary is dynamic and user based - so persistance beyond this run has
## meaning for me.  
myMadeUpDictionary = {"one": "banana", "two": "banana", "three": "banana", "four": "no-more"}

with open("res.txt", "wb") as myFile:
    pickle.dump(myMadeUpDictionary, myFile)
