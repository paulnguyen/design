package main

/*
	Go Cookbook:  https://www.safaribooksonline.com/library/view/go-cookbook/9781783286836/583187c3-fea4-4265-95f5-ec6fb81eefb2.xhtml

*/

import (
	"fmt"
	"net/http"
	"io/ioutil"
)

func main() {
	if err := Exec(); err != nil {
		panic(err)
	}
}



// APIClient is our custom client
type APIClient struct {
	*http.Client
}

// NewAPIClient constructor initializes the client with our
// custom Transport
func NewAPIClient(username, password string) *APIClient {
	t := http.Transport{}
	return &APIClient{
		Client: &http.Client{
			Transport: &APITransport{
				Transport: &t,
				username:  username,
				password:  password,
			},
		},
	}
}

// GetGoogle is an API Call - we abstract away
// the REST aspects
func (c *APIClient) GetGoogle() (int, error) {
	resp, err := c.Get("http://www.google.com")
	if err != nil {
		return 0, err
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	fmt.Println( string(body) )
	return resp.StatusCode, nil
}

// APITransport does a SetBasicAuth
// for every request
type APITransport struct {
	*http.Transport
	username, password string
}

// RoundTrip does the basic auth before deferring to the
// default transport
func (t *APITransport) RoundTrip(req *http.Request) (*http.Response, error) {
	req.SetBasicAuth(t.username, t.password)
	return t.Transport.RoundTrip(req)
}

// Exec creates an API Client and uses its
// GetGoogle method, then prints the result
func Exec() error {
	c := NewAPIClient("username", "password")

	StatusCode, err := c.GetGoogle()
	if err != nil {
		return err
	}
	fmt.Println("Result of GetGoogle:", StatusCode)
	return nil
}

