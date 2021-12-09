import './App.css';
import SseComponent from './components/SseComponent';

function App() {
  return (
    <div className="App">
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header> */}
      <div class="row">
      <div class="col-md-6">
      <SseComponent url = "http://localhost:8080/stream"/>
      </div>
      <div class="col-md-6 table2">
      <SseComponent url = "http://localhost:8080/stream2"/>
      </div>
      </div>
    </div>
  );
}

export default App;
