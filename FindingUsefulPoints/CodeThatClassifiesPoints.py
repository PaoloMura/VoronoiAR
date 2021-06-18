from Point import *

'''
A function that compares the distance of all the other points to a cast point
with the distance from that cast point to the point it was casted from and
returns either a closer point than the original one or the cast if the original
was already the closest one
'''
def Searching(_cast, _distance, _points):
    for _point in _points:
        if _point - _cast < _distance:
            return _point
    return _cast

# Just a handy function that writes in a given file all the points from an array
def WriteOn(file, array):
    for point in array:
        file.write(str(point.index) + ") " + str(point.x) + "," + str(point.y) + ","  + str(point.z) + "\n" )

file = open("Coordinates.txt", "r")

# Reading the corners (the first 4 points in the file) and framing them
Corners = []
for i in range(4):
    Corners.append(Point.Read(file.readline(), -1))

frame  = Frame.Create(Corners)

# Reading all the coordinates and creating our indexed points
Points = []
index = 0
for line in file:
    Points.append(Point.Read(line, index))
    index += 1

file.close()

# Lists that will come in handy later ;)
Less = []
Ful = []

'''
This litle loop casts a point onto the frame from all the existing points and
then checks to see if that point is the closest to its cast. If it is it's
stored in 'Ful' (from useful)
'''
for point in Points:
    cast = point.CastOnto(frame)
    distance = cast - point
    candidate = Searching(cast, distance, Points)
    if (candidate.index == cast.index):
        candidate = point
    if (candidate not in Ful):
        Ful.append(candidate)

# Less (from useless) gets all the point that were not in Ful appended to it
Less = [item for item in Points if item not in Ful]

"""
These were used for debugging

print(len(Points))
print(len(Ful))
print(len(Less))
"""

# Two txt files are created and our results are safed in theri appropriately named file
less = open("outUseless.txt", "w")
ful = open("outUseful.txt", "w")

WriteOn(less, Less)
WriteOn(ful, Ful)

less.close()
ful.close()

# Fin
