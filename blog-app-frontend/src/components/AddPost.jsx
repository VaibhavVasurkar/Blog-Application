import { Card, CardBody, Container, Form, Input, Label, Placeholder } from "reactstrap";

const AddPost = () => {
  return (
    <div className="wrapper">
      <Card>
        <CardBody>
          <h3>What going in your mind ?</h3>
          <Form>
            <div className="my-3">
              <Label for="title">Post title</Label>
              <Input
                type="text"
                id="title"
                placeholder="Enter here"
                className="rounded-0"
              />
            </div>

            <div className="my-3">
              <Label for="content">Post Content</Label>
              <Input
                type="textarea"
                id="content"
                placeholder="Enter here"
                className="rounded-0"
                style={{ height: "200px" }}
              />
            </div>

            <div className="my-3">
              <Label for="category">Post Category</Label>
              <Input
                type="select"
                id="category"
                placeholder="Enter here"
                className="rounded-0"
              >
                <option>Programming</option>
                <option>Movies</option>
                <option>Cricket</option>
                <option>Education</option>
                <option>Health</option>
              </Input>
            </div>

            <Container></Container>
          </Form>
        </CardBody>
      </Card>
    </div>
  );
};

export default AddPost;
