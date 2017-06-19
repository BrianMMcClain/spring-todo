const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {lists: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/lists'}).done(response => {
            this.setState({lists: response.entity});
    });
    }

    render() {
        return (
            <ToDoListList lists={this.state.lists}/>
    )
    }
}

class ToDoListList extends React.Component{
    render() {
        var lists = this.props.lists.map(list =>
            <ToDoList key={list.id} list={list}/>
    );
        return (
            <div className="container">
            <table className="table table-striped">
                <tbody>
                    <tr>
                        <th>Name</th>
                    </tr>
                    {lists}
                </tbody>
            </table>
            </div>
    )
    }
}

class ToDoList extends React.Component{
    render() {
        return (
            <tr>
            <td>{this.props.list.name}</td>
        </tr>
    )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)