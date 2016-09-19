
import pickle

class ObjectSample:
    name = ''
    num = 0.0


values = []

o1 = ObjectSample()

o1.name = 'MARIO'
o1.num = 12

values.append(o1)

o2 = ObjectSample()
o2.name = 'Jose'
o2.num = 1000


values.append(o2)

print(values)


file = open('res.txt','wb' )


pickle.dump(values, file)

file.close()
