import React, { Component } from 'react'
import { viewStores } from '../../service/OnlineService'
import './Stores.css'

export class Stores extends Component {
    constructor(props) {
        super(props)
      
        this.state = {
           stores : [],
           keyword : ''
        }
    }
    
      //Rendering data to the browser at the very first time
      componentDidMount(){
        //Call viewStores method to display all the stores from the database
        viewStores()
        .then( (response) => {
          console.log("All stores by Component" + JSON.stringify(response))
          this.setState({
            stores : response.data
          })
        })
      }

        //Search Box Change Handler
        searchChangeHandler = (event) =>{
          this.setState({
            keyword : event.target.value
         })
      }
    
      //Search Button On Click
      searchStore = (searchkey) =>{
        //Navigate to new route to render Search Component
          this.props.history.push(`/search/${searchkey}`)
         
      }
    
    render() {
        return (
            <div>
                <h1>ALL STORES</h1>
    
                {/* Search Text Box and Search Button */}
                <form>
                    {/* Input Search Box */}
                    <input type = "text" placeholder='Search Store Name or Description' value={this.state.keyword} 
                    onChange={this.searchChangeHandler}></input><br></br>
                    { /* Search Button - Take note to use button types instead of input type */}
                    <button onClick={ () => this.searchStore(this.state.keyword)} className="btn btn-danger">SEARCH</button>
                </form>
                
                {/* HTML Table */}
                <table class="styled-table">
                  <thead>
                      <tr>
                          <th>STORE NAME</th>
                          <th>STORE LOCATION</th>
                          <th>STORE PRODUCT</th>
                      </tr>
                  </thead>
                  <tbody>
                    {/* Retrieve all stores using map function */}
                  {
                    this.state.stores.map( store => 
                      <tr key={store.storeId}>
                          <td>{store.storeName}</td>
                          <td>{store.storeLocation}</td>
                          <td>{store.storeProduct}</td>
                      </tr>
                    )
                  }
                  </tbody>
              </table>
              
            </div>
        )
    }
    
}

export default Stores