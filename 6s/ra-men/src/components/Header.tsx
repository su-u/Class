import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const HeaderComponent = () => {
    return (
        <>
            <Header>
                <Title>
                    <Link to={`/`}>津田沼ラーメンマップ</Link>
                </Title>
            </Header>
        </>
    );
};

const Header = styled.div`
    top: 0;
    width: 100%;
    background-color: #202124;
    height: 60px;
    color: white;
    display: flex;
`;

const Title = styled.div`
    padding-left: 30px;
    padding-top: 10px;
    font-size: 30px;
    > a {
        text-decoration: none;
        color: white;
    }
`;

export default HeaderComponent;
