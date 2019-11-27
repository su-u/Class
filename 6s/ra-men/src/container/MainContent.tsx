import React from 'react';
import styled from 'styled-components';
import { RamenData } from "@/DataBase";

const Wrapper = styled.div`
    display: block;
    width: 80%;
`;

const Img = styled.img`
    width: 100%;
`;

interface Props {
    data: RamenData;
}

const MainContent = (props:Props) => {
    const { data } = props;
    const { name, img, pd } = data;
    return (
        <>
            <Wrapper>
                <h1>{name}</h1>
                <Img src={`img/${img}`} />
                <iframe
                    src={`https://www.google.com/maps/embed?pb=${pd}`}
                    width="600" height="450" frameBorder="0"></iframe>
            </Wrapper>
        </>
    );
};

export default MainContent;