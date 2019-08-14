#%%

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
x = 3 if condition else 4
print(x)


#%%
names = ['Tom', 'ppp', 'Taro']
index = 0

for name in names:
    print(index, name)
    index += 1

#%%
for index, name in enumerate(names, start=1):
    print(index, name)

#%%
new_dict = dict(enumerate(names))
print(new_dict)

