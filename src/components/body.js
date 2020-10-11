import React from 'react';
import 'antd/dist/antd.css';
import './body.css';
import {Layout} from 'antd';
import {Row, Col} from 'antd';

const {Content} = Layout;

class Body extends React.Component {
    render() {
        return (
            <Content>
                <Row>
                    <Col span={12}>
                        <p className="newsgen-p-source">Generate text from:</p>
                        <textarea rows={23} className="newsgen-textarea-source"/>
                    </Col>
                    <Col span={12}>
                        <div className="newsgen-layout-p-sameline ">
                            <p className="newsgen-p-target">Generate text with: </p>
                            <p className="newsgen-p">model-2020</p>
                        </div>
                        <textarea rows={23} className="newsgen-textarea-target"/>
                    </Col>
                </Row>
            </Content>
        );
    }
}

export default Body