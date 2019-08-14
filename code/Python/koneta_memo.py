#%%
from typing import List, Tuple

sum = 1_0_0_0
sum2 = 10_00_00_00
sum3 = 100_00

sum = sum2 + sum
print(sum)

print(f'{sum:,}')

#%%
condition = True

if condition:
    x = 1
else:
    x = 2

print(x)

#%%
condition = True
x:int = 3 if condition else 4
print(x)


#%%
names: List[str] = ['Tom', 'ppp', 'Taro']
index = 0

for name in names:
    print(index, name)
    index += 1

#%%
for index, name in enumerate(names, start=1):
    print(index, name)

#%%
from typing import Tuple
nameTuple: Tuple[int, str] = enumerate(names)
new_dict = dict(nameTuple)
print(new_dict)



