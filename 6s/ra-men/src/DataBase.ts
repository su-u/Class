export type RamenData = {
    name: string;
    desc: string;
    img: string;
}

export const DATA: RamenData[] = [
    {
        name: 'ramen1',
        desc: 'aa',
        img: 'aaa'
    }
];

export const ramenByName = (name: string) => DATA.find(x => x.name === name);