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
import { useState } from "react";
import { toast } from "react-toastify";
import { loginUser } from "../services/user-service";
import { doLogin } from "../auth";
import { useNavigate } from "react-router-dom";

const Login = () => {

  const navigate = useNavigate()

   const [loginDetail, setLoginDetail]=useState({
    email:'',
    password:''
  })

  const handleChange=(event, field)=>{
    let actualValue=event.target.value
    setLoginDetail({
      ...loginDetail,
      [field]:actualValue
    })
  }

  const handleReset = () =>{
    setLoginDetail({
      email:'',
      password:''
    })
  }

  const handleFormSubmit=(event)=>{
    event.preventDefault();
    console.log(loginDetail)

    if(loginDetail.email.trim()=='' || loginDetail.password.trim() =='' ){
      toast.error("Username or password is required !!")
      return;
    }

    //submit the data to server to generate token
    loginUser(loginDetail).then((data)=>{
      console.log(data)
      
      doLogin(data,()=>{
        console.log("login detail is saved to localstorage")

        //redirect to user dashboard page
        navigate("/user/dashboard")

      })

      toast.success("Login Success ")

    }).catch(error=>{
      console.log(error)
      if(error.response.status==400 || error.response.status==400){
        toast.error(error.response.data.message)
      }else{
      toast.error("something went wrong on server !!")
      }
    })

  }

  return (
    <Base>
      <Container>
        <Row className="mt-5">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card color="dark" outline>
              <CardHeader>
                <h3>Login Form </h3>
              </CardHeader>
              <CardBody>
                {/*  login form */}
                <Form onSubmit={handleFormSubmit}>
                  {/* email field */}
                  <FormGroup>
                    <Label for="email">Enter email</Label>
                    <Input
                      type="email"
                      placeholder="Enter email here"
                      id="email"
                      value={loginDetail.email}
                      onChange={(e)=>handleChange(e, 'email')}
                    />
                  </FormGroup>

                  {/* password field */}
                  <FormGroup>
                    <Label for="password">Enter password</Label>
                    <Input
                      type="password"
                      placeholder="Enter password here"
                      id="password"
                      value={loginDetail.password}
                      onChange={(e)=>handleChange(e,'password')}
                    />
                  </FormGroup>
                  <Container className="text-center">
                    <Button color="success">Login</Button>
                    <Button color="danger" className="ms-4" type="reset" onClick={handleReset}>
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

export default Login;
