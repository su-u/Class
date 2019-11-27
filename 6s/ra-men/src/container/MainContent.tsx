import React from 'react';
import styled from 'styled-components';
import { RamenData } from "@/DataBase";

const Wrapper = styled.div`
    display: block;
    width: 80%;
`;

interface Props {
    data: RamenData;
}

const MainContent = (props:Props) => {
    const { data } = props;
    const { name } = data;
    return (
        <>
            <Wrapper>
                {name}
            </Wrapper>
        </>
    );
};

export default MainContent;