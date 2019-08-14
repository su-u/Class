# %%
from typing import List

sum = 1_0_0_0
sum2 = 10_00_00_00
sum3 = 100_00

sum = sum2 + sum
print(sum)

print(f'{sum:,}')

# %%
condition = True

if condition:
    x = 1
else:
    x = 2

print(x)

# %%
condition = True
x: int = 3 if condition else 4
print(x)

# %%
names: List[str] = ['Tom', 'ppp', 'Taro']
index = 0

for name in names:
    print(index, name)
    index += 1

# %%
for index, name in enumerate(names, start=1):
    print(index, name)

# %%
from typing import Tuple

nameTuple: Tuple[int, str] = enumerate(names)
new_dict = dict(nameTuple)
print(new_dict)

# %%
from typing import Set

names: List[str] = ['Tom', 'Sum', 'Kappa']
ageList: List[int] = [20, 30]
deptList: List[str] = ['Business', 'HR']

for name, age, dept in zip(names, ageList, deptList):
    print(f'{name}: {age}: {dept}')

print()
for value in zip(names, ageList, deptList):
    print(value)

print()

from itertools import zip_longest

for value in zip_longest(names, ageList, deptList, fillvalue=0):
    print(value)

# %%
numList: List[int] = [1, 2, 3, 4, 5, 6]

a, b, *_ = numList
print(a, b)
