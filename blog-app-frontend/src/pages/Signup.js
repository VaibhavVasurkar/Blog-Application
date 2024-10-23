import {
  Button,
  Card,
  CardBody,
  CardHeader,
  Col,
  Container,
  Form,
  FormGroup,
  Input,
  Label,
  Row,
} from "reactstrap";
import Base from "../components/Base";
import { useEffect, useState } from "react";

const Signup = () => {

  const [data, setData]=useState({
    name:'',
    email:'',
    password:'',
    about:''
  })

  const [error,setError]=useState({
    errors:{},
    isError:false
  })


  //handle change
  const handleChange=(event,property)=>{
   
    setData({...data,[property]:event.target.value})

  }

  //reseting form
  const resetData=()=>{
    setData({
      name:'',
      email:'',
      password:'',
      about:''
    })
  }
 
  //submit the form 
  
  const submitForm=(event)=>{
    event.preventDefault()

    console.log(data)

    //data validations

    //call server api for sending data
  }


  return (
    <Base>
      <Container>
        <Row className="mt-4">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card color="dark" outline>
              <CardHeader>
                <h3>Fill infromation to register !!</h3>
              </CardHeader>
              <CardBody>
                {/* creattion form */}

                <Form onSubmit={submitForm}>
                  {/* name field */}
                  <FormGroup>
                    <Label for="name">Enter Name</Label>
                    <Input
                      type="text"
                      placeholder="Enter name here"
                      id="name"
                      onChange={(e)=>handleChange(e,'name')}
                      value={data.name}
                    />
                  </FormGroup>

                  {/* email field */}
                  <FormGroup>
                    <Label for="email">Enter Email</Label>
                    <Input
                      type="email"
                      placeholder="Enter email here"
                      id="email"
                      onChange={(e)=>handleChange(e,'email')}
                      value={data.email}
                    />
                  </FormGroup>

                  {/* password field */}
                  <FormGroup>
                    <Label for="password">Enter Password</Label>
                    <Input
                      type="password"
                      placeholder="Enter password here"
                      id="password"
                      onChange={(e)=>handleChange(e,'password')}
                      value={data.password}
                    />
                  </FormGroup>

                  {/* about field */}
                  <FormGroup>
                    <Label for="about">Enter about</Label>
                    <Input
                      type="textarea"
                      placeholder="Enter about here"
                      id="about"
                      onChange={(e)=>handleChange(e,'about')}
                      value={data.about}

                    />
                  </FormGroup>
                  <Container className="text-center">
                    <Button color="success">Register</Button>
                    <Button onClick={resetData} color="warning" className="ms-4" type="reset">
                      Reset
                    </Button>
                  </Container>
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </Base>
  );
};

export default Signup;
