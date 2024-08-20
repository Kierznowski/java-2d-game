'''This script translate map painted in pixels to numeric file,
    which can be understanded by TileManager.'''

from PIL import Image

# load file
fileName = 'island-1.png'
pixelMap = Image.open('./java-2d-game/Java2DGame/src/main/resources/maps/' + fileName).convert("RGB")
px = pixelMap.load()

# some variables
width, height = pixelMap.width, pixelMap.height
rgb_to_number = {(0, 171, 255): 1, (247, 245, 71): 4, (9, 255, 0): 0, (29, 170, 0): 3, (172, 110, 0): 5}

# prepare numbers of tiles regarding to pixels rgb
tileNumber = [[0 for _ in range(width)] for _ in range(height)]
for y in range(height):
    for x in range(width):
        if px[x, y] == (0, 0, 0):
            continue
        else:
            tileNumber[y][x] = rgb_to_number.get(px[x, y], 0)

# Create new map.txt with numeric values
txtFileName = fileName.strip("png")

with open('./java-2d-game/Java2DGame/src/main/resources/maps/'+ txtFileName + 'txt', 'w', newline='\n') as file:
    for row in tileNumber:
        for i in range(len(row)):
            file.write(str(row[i]) + ' ')
            if(i == len(row) - 1):
                file.write("\n")

