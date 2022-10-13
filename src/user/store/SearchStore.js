import React, { Component } from 'react'
import { searchStore } from '../../service/OnlineService'
import './Stores.css'

export class SearchStore extends Component {
    constructor(props) {
        super(props)
      
        this.state = {
           stores: [],
           searchkey: this.props.match.params.searchkey
        }
      }
  
      //Render data calling searchStore method from the service
      componentDidMount(){
        searchStore(this.state.searchkey)
        .then( (response) => {
          console.log("All stores by Component" + JSON.stringify(response))
          this.setState({
            stores: response.data
            })
            
        })

      }
      render() {
        return (
            <div>
                <h1>SEARCH RESULTS</h1>
    
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
                    this.state.stores.map(store => 
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

export default SearchStore