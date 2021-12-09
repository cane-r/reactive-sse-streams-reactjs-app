import React from "react";
import { Table } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';


export default class SseComponent extends React.Component {

    constructor(props){
        super(props);
        this.state = {data:[]};
        if(!props.url) {
          throw new Error("No stream url is given for this component.");
        }
        this.eventSource = new EventSource(props.url);

        this.eventSource.onopen = (event) => {
            console.log("Opened the SSE connection")
          }
          
        this.eventSource.onmessage = (event) => {
           // [...data,...event.data]
            this.state.data.push(event.data);
            this.setState({
                data: this.state.data
            });
          }
          
        this.eventSource.onerror = (event) => {
            this.eventSource.close();
          }
      }

    componentDidMount(){
    }

      render () {
          return (
              <Table bordered dark hover responsive striped>
                <thead>
                  <tr>
                    <th scope="row">
                      #
                    </th>
                    <th scope="row">
                      Data
                    </th>
                  </tr>
                </thead>
                <tbody>
                  {this.state.data.map((data,i) =>
                    <tr key={i}>
                      <th scope="row">
                        {i}
                      </th>
                      <td>
                        {data}
                      </td>
                    </tr>
                  )}
                </tbody>
              </Table>
          );
      }
  }